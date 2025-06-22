.data
prompt1: .asciiz "Introduza um numero\n"
result: .asciiz "\n O numero em hexadecimal é: "

.text
.globl main

main: la $a0, prompt1
      li $v0, 4 #print string
      syscall
      
      li $v0, 5 #read int
      syscall
      move $t1, $v0
      
      la $a0, result
      li $v0, 4 #print string
      syscall
      
       #store in t0
      li $t0, 0
      li $t3, 0xF0000000
      

for: beq $t0, 8, done
     and $t2, $t1, $t3
     srl $t2, $t2, 28
     li $t4, 10
     blt $t2, $t4, digit 
     addi $a0, $t2, 55
     j print
digit:
      addi $a0, $t2, 48    
print:
      li $v0, 11           
      syscall
      sll $t1, $t1, 4      
      add $t0, $t0, 1      
      j for
done:li $v0, 10
     syscall
     
