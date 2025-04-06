# CS 1501 Assignment 1 (8 Points): Boggle Word Finder with Wildcards and Blocked Cells

## 📘 Overview
In this assignment, you will implement a method that finds all valid English words of a given length on a special Boggle board. The board includes:

- **Regular letters (A–Z)**
- **Wildcards `*`** — represent any letter
- **Blocked cells `#`** — cannot be used or visited

You will use a **recursive backtracking approach** to explore the board and a provided **`DictInterface`** to validate words and prefixes.

---

## ✏️ Your Task
You are provided with an interface named `A1Interface.java` which declares the method:

```java
Set<String> getWordsOfLength(char[][] boggleBoard, DictInterface dictionary, int wordLength);
```

You must implement this method in a class named `A1.java` that implements `A1Interface`. Your implementation **must exist in the `src/main/java` folder** of your repository.

🔒 **You are only allowed to modify the `A1.java` file. Do not make changes to any other files in the repository.**

---

## 📋 Input Details
- `boggleBoard` is a square 2D array of characters (e.g., `char[][] board = {{'C','A','T'}, {'R','*','#'}, {'T','A','R'}}`)
- Valid characters:
  - A–Z uppercase
  - `*` wildcard (matches any letter)
  - `#` blocked cell (cannot be visited)
- `dictionary` supports the method:
  ```java
  int searchPrefix(StringBuilder s); // 0 = invalid, 1 = prefix, 2 = word, 3 = word & prefix
  ```

---

## 🔍 Rules
- The board is always **square** (same number of rows and columns).
- You may move in **all 8 directions** (N, NE, E, SE, S, SW, W, NW).
- You may **not reuse** the same cell in one word.
- Wildcards (`*`) can be substituted for any letter A–Z.
- Blocked cells (`#`) cannot be used or entered.
- Only **exactly** `wordLength` words that are in the dictionary should be returned.

---

## 🧪 Example
Given this board:
```
C A T
R * #
T A R
```
And a dictionary containing: `CAT`, `CAR`, `RAT`, `CAP`, `ART`, `TAR`

Then:
```java
getWordsOfLength(board, dictionary, 3);
```
should return:
```
[CAT, CAR, RAT, CAP, ART, TAR]
```
(Depending on how the wildcard is matched)

---

## 🛠️ Recommended Structure
- Use a recursive helper method for backtracking.
- Track visited cells.
- Use a `StringBuilder` for the current word.
- Use the dictionary to **prune** invalid paths early.

---

## ✅ Submission Checklist
- [x] Implemented `getWordsOfLength(...)` in `A1.java`
- [x] Class `A1` implements `A1Interface`
- [x] `A1.java` is located in `src/main/java`
- [x] Handles wildcards correctly
- [x] Does not revisit cells or include blocked ones
- [x] Code is readable and well-documented
- [x] Passes all test cases
- [x] Did not modify any file other than `A1.java`

---

## 🧪 How to Compile and Run the Tests
This project uses **JUnit 4** and **Maven**.

### ▶️ To compile the project:
```bash
mvn compile
```

### ▶️ To run all tests:
```bash
mvn test
```

### ✅ Project Structure:
```
src/
  main/
    java/
      A1Interface.java       // provided interface
      A1.java                // your implementation (must be here)
  test/
    java/
      A1Test.java            // provided unit tests
pom.xml                      // Maven build file
```

Make sure your implementation compiles and all tests pass locally **before submitting**.

---

## 📤 Gradescope Autograder
- This assignment will be autograded on **Gradescope**.
- The autigrader reads only your `A1.java` file.
- The autograder will:
  - Compile your code using Maven
  - Run unit tests using JUnit
  - Check for correctness and edge cases

💡 You can submit as many times as you'd like before the deadline — only the latest submission counts.

---

Good luck, and happy Boggling! 🔤✨

