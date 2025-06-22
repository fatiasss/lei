.data
prompt1: .asciiz "Introduza um numero\n"
result: .asciiz "A soma de todos os números é: "

.text
.globl main

main: la $a0, prompt1
      li $v0, 4 #print string
      syscall
      li $t0, 0
      

for: beq $t0, 5, done
     li $v0, 5 #read int
     syscall
     move $t1, $v0 #store in t0
     add $t2, $t2, $t1
     add $t0, $t0, 1
     j for
     
done: la $a0, result
      li $v0, 4 #print string
      syscall
      addi $a0, $t2, 0
      li $v0, 1 #print int
      syscall
