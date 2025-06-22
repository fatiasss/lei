.data
prompt1: .asciiz "Introduza um numero\n"
result: .asciiz "O fatorial do numero inserido é: "

.text
.globl main

main: la $a0, prompt1
      li $v0, 4 #print string
      syscall
      
      li $v0, 5 #read int
      syscall
      move $t0, $v0 #store in t0
      
      li $t1, 1
      
for: beqz $t0, done
     mul $t1, $t1, $t0
     sub $t0, $t0, 1
     j for
     
done: la $a0, result
      li $v0, 4 #print string
      syscall
      addi $a0, $t1, 0
      li $v0, 1 #print int
      syscall
