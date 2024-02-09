# java-assessment

Run `git clone https://github.com/rhllh/java-assessment.git` in your local folder.

## task #1 (encoder-decoder)

1. `cd encoder-decoder`

```mvn compile exec:java -Dexec.mainClass="com.dxc.assessment.App" -Dexec.args="<type> <offsetCharacter> '<plainOrEncodedText>'"```

2. To **encode** "I PLAY BY THE RULES" with an offset character of 'B', the arguments should be `E B 'I PLAY BY THE RULES'`

3. To **decode** with an offset of F, the arguments should be `D F '<string to decode>'`

## task #2 (login application)

**backend**: java 17, spring boot 3.1.4, spring security 6\
**server-side template**: thymeleaf\
**database**: mysql

1. `cd login-app`

2. Run the schema file `schema.sql` in MySQL.

3. Run `mvn clean spring-boot:run` in current folder to start application.

4. Use the following login credentials:

| username | password             | roles         |
|----------|----------------------|---------------|
| michael  | michael-password-123 | user          |
| mabel    | mabel-wordpass-098   | user, manager |
