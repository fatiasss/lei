
#assuming int a is in $a0 and int b is in $a1

product:addi $sp, $sp, -16     # Allocate stack space
    	sw $ra, 12($sp)        # Save return address
    	sw $a0, 8($sp)         # Save a
    	sw $a1, 4($sp) 
	li $t0, 0 #r = 0
        rem $t1, $a0, 2
	beq $t1, 0, even
odd:	move $t0, $a1
rest: 	beq $a0, 0, return_zero
        srl $a0, $a0, 1
	sll $a1, $a1, 1
	jal product
        add $t0, $t0, $v0
        move $v0, $t0
        j end
        
even: j rest
        
return_zero: move $v0, 0
             j end

end : 
    lw $ra, 12($sp)        # Restore return address
    lw $a0, 8($sp)         # Restore a
    lw $a1, 4($sp)         # Restore b
    addi $sp, $sp, 16      # Restore stack
    jr $ra                 # Return
