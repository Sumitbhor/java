
---

# 📘 Managing I/O in Java

## 🔹 What is I/O?
- *I/O (Input/Output)* refers to how a program communicates with the outside world (keyboard, files, console, network).
- In Java, I/O is handled through *streams*.

---

## 1. Streams in Java
### 🔹 Definition
- A *stream* is a sequence of data (like a pipeline).
- Two main categories:
  - *Byte Streams* → handle binary data (images, files).
  - *Character Streams* → handle text data (Unicode characters).

### 🔹 Byte Streams
- Classes: InputStream, OutputStream
- Examples:
java
FileInputStream fin = new FileInputStream("data.txt");
FileOutputStream fout = new FileOutputStream("copy.txt");


### 🔹 Character Streams
- Classes: Reader, Writer
- Examples:
java
FileReader fr = new FileReader("data.txt");
FileWriter fw = new FileWriter("output.txt");


---

## 2. Predefined Streams
Java provides *three standard streams*:
- System.in → Standard input (keyboard).
- System.out → Standard output (console).
- System.err → Standard error output.

Example:
java
System.out.println("Hello World");  // Output stream
System.err.println("Error occurred");  // Error stream


---

## 3. Reading Console Input
### 🔹 Using Scanner
java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
System.out.print("Enter name: ");
String name = sc.nextLine();
System.out.println("Hello " + name);


### 🔹 Using BufferedReader
java
import java.io.*;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
System.out.print("Enter age: ");
int age = Integer.parseInt(br.readLine());
System.out.println("Age: " + age);


---

## 4. Writing Console Output
- *Basic Output*
java
System.out.println("This is output");


- **Formatted Output with printf**
java
System.out.printf("Name: %s, Age: %d", "Parikshit", 20);


---

## 5. PrintWriter Class
- Provides *formatted, convenient output*.
- Can write to console or files.
java
import java.io.PrintWriter;

PrintWriter pw = new PrintWriter(System.out, true);
pw.println("Using PrintWriter for output");


---

# 📝 Summary Table

| Concept            | Classes/Methods | Example |
|--------------------|-----------------|---------|
| Byte Streams       | InputStream, OutputStream | FileInputStream, FileOutputStream |
| Character Streams  | Reader, Writer | FileReader, FileWriter |
| Predefined Streams | System.in, System.out, System.err | System.out.println() |
| Console Input      | Scanner, BufferedReader | sc.nextLine(), br.readLine() |
| Console Output     | System.out, printf, PrintWriter | pw.println() |

---

✅ These notes cover *theory + syntax + examples*.  
👉 For your *OOP mini-project (Library Management System)*, you’ll likely use:
- Scanner for user input (book details, student info).
- PrintWriter or FileWriter for saving records to files.
- System.out for displaying menus.

# 📘 Byte Streams in Java

## 🔹 What is a Byte Stream?
- A *Byte Stream* is used to perform *input and output of 8-bit bytes*.
- It is the *lowest-level I/O mechanism* in Java.
- Suitable for:
  - Binary data (images, audio, video, executables).
  - File transfer and network communication.
- Located in the **java.io package**.

---

## 🔹 Hierarchy of Byte Streams
Java provides two abstract classes:
- **InputStream** → for reading data (input).
- **OutputStream** → for writing data (output).

All byte stream classes inherit from these.

### Common Subclasses:
| InputStream | OutputStream |
|-------------|--------------|
| FileInputStream | FileOutputStream |
| BufferedInputStream | BufferedOutputStream |
| DataInputStream | DataOutputStream |
| ObjectInputStream | ObjectOutputStream |

---

## 🔹 Key Methods

### InputStream
- int read() → reads one byte, returns -1 at end of file.
- int read(byte[] b) → reads bytes into an array.
- void close() → closes the stream.

### OutputStream
- void write(int b) → writes one byte.
- void write(byte[] b) → writes an array of bytes.
- void flush() → forces data to be written.
- void close() → closes the stream.

---

## 🔹 Example 1: Reading a File using FileInputStream
java
import java.io.*;

class ByteStreamDemo {
    public static void main(String[] args) {
        try {
            FileInputStream fin = new FileInputStream("input.txt");
            int i;
            while((i = fin.read()) != -1) {
                System.out.print((char)i);  // Convert byte to char
            }
            fin.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

👉 Reads file byte by byte and prints characters.

---

## 🔹 Example 2: Writing to a File using FileOutputStream
java
import java.io.*;

class ByteStreamDemo {
    public static void main(String[] args) {
        try {
            FileOutputStream fout = new FileOutputStream("output.txt");
            String data = "Hello Parikshit, Byte Stream Example!";
            fout.write(data.getBytes());  // Convert string to bytes
            fout.close();
            System.out.println("Data written successfully.");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

👉 Writes string data into a file as bytes.

---

## 🔹 Example 3: Buffered Streams (Efficient I/O)
java
import java.io.*;

class BufferedDemo {
    public static void main(String[] args) throws Exception {
        FileInputStream fin = new FileInputStream("input.txt");
        BufferedInputStream bin = new BufferedInputStream(fin);

        int i;
        while((i = bin.read()) != -1) {
            System.out.print((char)i);
        }
        bin.close();
        fin.close();
    }
}

👉 Buffered streams improve performance by reducing disk access.

---

## 🔹 Example 4: Data Streams (Primitive Data Types)
java
import java.io.*;

class DataStreamDemo {
    public static void main(String[] args) throws Exception {
        FileOutputStream fout = new FileOutputStream("data.bin");
        DataOutputStream dout = new DataOutputStream(fout);

        dout.writeInt(25);
        dout.writeDouble(99.99);
        dout.writeUTF("Hello Byte Stream");
        dout.close();

        FileInputStream fin = new FileInputStream("data.bin");
        DataInputStream din = new DataInputStream(fin);

        System.out.println(din.readInt());
        System.out.println(din.readDouble());
        System.out.println(din.readUTF());

        din.close();
    }
}

👉 Allows writing/reading *int, double, String* directly.

---

## 🔹 Advantages of Byte Streams
- Can handle *all types of data* (text + binary).
- Efficient with buffering.
- Flexible (supports chaining with decorators like BufferedInputStream).

## 🔹 Limitations
- Not human-readable (raw bytes).
- For text data, *Character Streams* (Reader, Writer) are more convenient.

---

# 📝 Quick Summary Table

| Feature | InputStream | OutputStream |
|---------|-------------|--------------|
| Base Class | InputStream | OutputStream |
| Common Subclasses | FileInputStream, BufferedInputStream, DataInputStream | FileOutputStream, BufferedOutputStream, DataOutputStream |
| Key Methods | read(), read(byte[]), close() | write(int), write(byte[]), flush(), close() |
| Use Cases | Reading files, images, binary data | Writing files, saving binary data |

---

✅ With this, you now have *theory + hierarchy + examples* for Byte Streams.  
👉 Since you’re building projects like a *Library Management System, you’ll mostly use **Character Streams* for text files, but Byte Streams are essential when handling *binary files (images, serialized objects, backups)*.

