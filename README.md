# sp0
###### postav'te 3 &lt;з

Терминалы:
* VAR -> [a-z]+
* ASSIGN_OP -> =
* DIGIT -> 0 | ( [1-9][0-9]* )
* OP -> +|-|*|/
* LOGIC_OP -> >|<|=|!=|<=|>=
* L_B -> (
* R_B -> )
* L_SB -> {
* R_SB -> }
* DO_KW -> do
* WHILE_KW -> while

Нетерминалы:
* lang -> expr+
* expr -> assign_expr | do_while_expr
* assign_expr -> VAR ASSIGN_OP expr_value
* expr_value -> value (OP value)*
* logic_expr -> value LOGIC_OP value
* value -> VAR | DIGIT
* do_while_expr -> (do_expr while_expr) | (while_expr)
* do_expr -> DO_KW L_SB expr R_SB
* while_expr -> while_head while_body
* while_head -> L_B logic_expr R_B
* while_body -> L_SB expr R_SB
* br_expr -> L_B expr_value R_B | L_B br_expr* R_B
