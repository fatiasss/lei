.data
nums: .word -2, 3, 6, 4, 8, 126, -131, 17

.globl main
.text 

main: li $t0, 0 # t0 = i = 0
      la $t1, nums  #nums address
      

for: bge $t0, 8, done
     sll $t2, $t0, 2 # t2 = ti*4
     add $t3, $t2, $t1 #nums[i]
     lw $s0, 0($t3) #word of nums[i]
     andi $t3, $s0, 1 #masks all bits except the last one
     beq $t3, $zero, if
     j else
     
if: move $a0, $s0
    li $v0, 1
    syscall
    li $a0, ','
    li $v0, 11
    syscall
    addi $t0, $t0, 1
    j for

else: addi $t0, $t0, 1
      j for

done: li $v0, 10
      syscall