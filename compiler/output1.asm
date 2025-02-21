	.data
	nL:    .asciiz    "\n"
	varx:	 .word 	 0
	vary:	 .word 	 0
	.text
	.globl main
	main: 
	li $v0 2
	la $t1 varx
	sw $v0, ($t1)
	la $t1 varx
	lw $v0 ($t1)
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 1
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $t0 $v0
	la $t1 vary
	sw $v0, ($t1)
	la $t1 varx
	lw $v0 ($t1)
	subu $sp $sp 4
	sw $v0 ($sp)
	la $t1 vary
	lw $v0 ($t1)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $t0 $v0
	la $t1 varx
	sw $v0, ($t1)
	la $t1 varx
	lw $v0 ($t1)
	subu $sp $sp 4
	sw $v0 ($sp)
	la $t1 vary
	lw $v0 ($t1)
	lw $t0 ($sp)
	addu $sp $sp 4
	mult $t0 $v0
	mflo $v0
	move $a0 $v0
	li $v0 1
	syscall
	la $a0, nL
	li $v0, 4
	syscall
	la $t1 varx
	lw $v0 ($t1)
	subu $sp $sp 4
	sw $v0 ($sp)
	la $t1 vary
	lw $v0 ($t1)
	lw $t1 ($sp)
	addu $sp $sp 4
	ble $t1, $v0, elseif1
	la $t1 varx
	lw $v0 ($t1)
	move $a0 $v0
	li $v0 1
	syscall
	la $a0, nL
	li $v0, 4
	syscall
	la $t1 vary
	lw $v0 ($t1)
	move $a0 $v0
	li $v0 1
	syscall
	la $a0, nL
	li $v0, 4
	syscall
	j endif1
elseif1:
endif1:
	li $v0 0
	la $t1 varx
	sw $v0, ($t1)
while2:
	la $t1 varx
	lw $v0 ($t1)
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 10
	lw $t1 ($sp)
	addu $sp $sp 4
	bge $t1, $v0, endwhile2
	la $t1 varx
	lw $v0 ($t1)
	move $a0 $v0
	li $v0 1
	syscall
	la $a0, nL
	li $v0, 4
	syscall
	la $t1 varx
	lw $v0 ($t1)
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 1
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $t0 $v0
	la $t1 varx
	sw $v0, ($t1)
	j while2
endwhile2:
	li $v0 10
	syscall
