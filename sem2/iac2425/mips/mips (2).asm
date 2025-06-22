 .data
prompt: .ascii "Introduza dois números :"
prompt2: .ascii "A soma dos números e’:"
 .text
 .globl main
main: la $a0, prompt #print the prompt
li $v0, 4
syscall

li $v0, 5 #read int
syscall
move $t0, $v0

li $v0, 5 #read int
syscall
move $t1, $v0


add $t3 ,$t0, $t1

la $a0, prompt2 #print the prompt2
li $v0, 4
syscall



move $a0, $t3
li $v0, 1 #print int10
#li $v0, 36 #print intu10
syscall




