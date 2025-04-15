terminal = r'^([0-9])([0-9a-z]*)$|^(true|false)$'
nterminal = r'\\wedge|\\vee|\\rightarrow|\\leftrightarrow'

class InvalidExpr(Exception):
    pass

def formulaunaria(token_index):
  token_index = formula(token_index)

  if tokens[token_index] != ")":
    raise InvalidExpr

  return token_index + 1

def formulabinaria(token_index):
  token_index = formula(token_index)
  token_index = formula(token_index)

  if tokens[token_index] != ")":
    raise InvalidExpr

  return token_index + 1

def formula(token_index):
  if tokens[token_index] == "(":
    token_index += 1

    if tokens[token_index] == r"\neg":
      token_index = formulaunaria(token_index+1)
    elif bool(re.fullmatch(nterminal, tokens[token_index])):
      token_index = formulabinaria(token_index+1)
    else:
      raise InvalidExpr
  elif bool(re.fullmatch(terminal, tokens[token_index])):
    return token_index + 1
  else:
    raise InvalidExpr
  return token_index


expr = r"( \wedge 3x ( \leftrightarrow ( \neg 7f ) 5y ) )"

tokens = expr.split(" ")

try:
  formula(0)
  print("Expressao valida.")
except InvalidExpr as e:
  print("Expressao invalida.")
