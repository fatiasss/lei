.data
prompt1: .asciiz "Introduza um numero\n"
dash: .asciiz "-"

.text
.globl main

main: la $a0, prompt1
      li $v0, 4 #print string
      syscall
      
      li $v0, 5 #read int
      syscall
      move $t2, $v0 #store in t2
      
      li $t1, 0
      li $t0, 0
      
for: beq $t0, $t2, done
     add $a0, $t0, 0 #load int
     li $v0, 1 #print int
     syscall
     la $a0, dash
     li $v0, 4 #print int
     syscall
     addi $t0, $t0, 1
     j for
     
done:li $v0, 10
     syscall
