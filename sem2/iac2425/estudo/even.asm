.data 
nums: .word -2,3,6,4,8,126,-131,17

.text
.globl main

main: li $t0, 0
      li $t1, 0
      la $a1, nums

for: bge $t0, 8, done
    sll $t1, $t0, 2
    add $t2, $a1, $t1
    lw $t3, 0($t2) 
    andi $t4, $t3, 1
    addi $t0, $t0, 1
if:  beq $t4, $zero, even
     j for
     
even: move $a0, $t3
      li $v0, 1
      syscall
      li $a0, ','
      li $v0, 11
      syscall
      j for
done: li $v0, 10
      syscall