# Luca Takemura Piccoli

import re

token_specification = [
  ('LPAREN', r'\('),
  ('RPAREN', r'\)'),
  ('OPS', r'\\neg|\\wedge|\\vee|\\rightarrow|\\leftrightarrow'),
  ('PROPS', r'(true|false)|[0-9][0-9a-z]*'),
  ('SKIP', r'[ \t\n]+'),
  ('MISMATCH', r'.'),
]

tokens_regex = '|'.join(f'(?P<{name}>{pattern})' for name, pattern in token_specification)
compiled_regex = re.compile(tokens_regex)

def anal_lex(expr):
  tokens = []
  for mo in compiled_regex.finditer(expr):
    token_type = mo.lastgroup
    token_value = mo.group(token_type)
    if token_type == 'SKIP':
      continue
    elif token_type == 'MISMATCH':
      raise InvalidExpr
    else:
      tokens.append({'type': token_type, 'value': token_value})
  return tokens

class InvalidExpr(Exception):
  pass

def parser(index, tokens):
  if index >= len(tokens):
    raise InvalidExpr

  current = tokens[index]

  if current['type'] == 'LPAREN':
    index += 1
    if index >= len(tokens):
      raise InvalidExpr
    op_token = tokens[index]
    if op_token['type'] != 'OPS':
      raise InvalidExpr
    if op_token['value'] == r'\neg':
      index += 1
      index = parser(index, tokens)
    else:
      index += 1
      index = parser(index, tokens)
      index = parser(index, tokens)
    if index >= len(tokens) or tokens[index]['type'] != 'RPAREN':
      raise InvalidExpr
    return index + 1

  elif current['type'] == 'PROPS':
    return index + 1

  else:
    raise InvalidExpr

  return index

if __name__ == "__main__":

  print("Digite o nome do arquivo: ")
  filename = input()

  with open(filename, 'r') as f:
    n_expr = int(f.readline())
    for i in range(n_expr):
      expr = f.readline()

      try:
        tokens = anal_lex(expr)

        final_index = parser(0, tokens)
        if final_index != len(tokens):
          raise InvalidExpr
        print("valida.")

      except InvalidExpr:
        print("inválida.")
