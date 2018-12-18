# ll1-calculator

In order to compile Calculadora.lex you need to download jflex http://www.jflex.de/download.html, copy it in the directory and type in the shell:
```jflex Calculadora.lex```
After that a file called Yytoken.java will be created. Finally type ```javac *.java``` and execute the program with java Calculadora file.in > file.out where file.in is your test and file.out the output.
Below are some examples:
```
exp.in	

2 + 2
3 * 5
12 * 400

exp.out

4
15
4800

```
```

exp.in	

21 + 12 
11 * 22     

5 - 1
123

exp.out

33
242

4
123

```
```

exp.in	

10 + 20 * 30
10 * 20 + 30
30 * 20 +10
1 * 2 + 3 * 4 + 5 * 6 + 7 * 8

exp.out

610
230
610
100
```
```
exp.in

1+-2--3*-4
-11+22-0+4*-1
2+32/2/2/2
- - - - 3
64/-2/2/2/-2*-2*-2

exp.out

-13
7
6
3
16
```
```
exp.in

(1+-2)-(-3*-4)
2 * ((-11+22-0)+(4*-1))
2 + (((32/2)/2)/2)
- (- - - 3 * (2 + 5))
(64/-2/2/2/-2)-(2*-2*-2)
((((((1+2)*3)+4)*((5+6)*7)+8)*9))

exp.out

-13
14
6
21
-4
9081
```
