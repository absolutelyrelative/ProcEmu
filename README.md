## Travis-Build
[![Build Status](https://api.travis-ci.org/absolutelyrelative/ProcEmu.svg?branch=master)](https://travis-ci.org/absolutelyrelative/ProcEmu)

# Readme
## ProcEmu
A processor emulator application, allowing electronics / engineering students to emulate Single Cycle, Multi Cycle, Pipeline processors and their respective components (ALU, Controller, ALU Controller, various memories, Program Counter) to be inspected at each emulated clock and/or instruction. It also contains its own assembler to assemble instructions into binary code that is loaded in the Instruction Memory (if Single Cycle / Pipeline).

## Instruction Set

### Type 1 Instructions
Type 1 instructions follow the following pattern:
```
bits    data
0-15    OFFSET
16-20   RS/RD
21-25   RO
26-31   OP
```
We have two instructions, LW to load a word into a register, and SW to store a value from a register to Data Memory. 
*Examples:*
```as
>LW R1, 200(R2):
OP:      35
RO:      02
RD:      01
OFFSET: 200
```
```as
>SW R1, 200(R2):
OP:      43
RO:      02
RS:      01
OFFSET: 200
```

### Type 2 Instructions
Type 2 instructions follow the following pattern:
```
bits    data
0-5     FUNCT
6-10    SHIFT (Not implemented)
11-15   RD
16-20   RT
21-25   RS
26-31   OP (Always 0 for type 2 instructions)
```
We have two instructions, ADD to perform a sum between two registers, and SUB to perform a subtraction between two registers.
*Examples:*
```as
>ADD R8,R17,R18: (R18 + R17) |---> R8
OP:       0
RS:      17
RT:      18
RD:      08
SHAMT:    0
FUNCT:   32
```
```as
>SUB R8,R17,R18: (R18 - R17) |---> R8
OP:       0
RS:      17
RT:      18
RD:      08
SHAMT:    0
FUNCT:   34
```

### Type 3 Instructions
Type 3 instructions follow the following pattern:
```
bits    data
0-15    OFFSET
16-20   RT
21-25   RS
26-31   OP (Only beq is implemented, so always 4)
```
We only have one type 3 instruction, BEQ, which compares the values of two registers and, if equal, will modify the behaviour of the Program Counter.
*Examples:*
```as
>BEQ R18,R19,400:
OP:          4
RS:         18
RT:         19
OFFSET:    400
```

### Type 4 Instructions
Type 4 instructions follow the following pattern:
```
bits data 
0-25 OFFSET
26-31 OP
```
We only have one type 4 instruction, JMP, which modifies the behaviour of the Program Counter with no conditions.
*Examples:*
```
>JMP 400:
OP:          2
OFFSET:    400
```


**Free Software, Hell Yeah!**
