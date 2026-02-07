
---

# ✅ 1. What is Reflection?

**Reflection = Ability of a program to examine and modify itself at runtime.**

In simple words:

> Java Reflection lets you **see and use classes, methods, variables, constructors at runtime**, even if you don’t know their names at compile time.

Example:
Normally:

```java
Student s = new Student();
s.show();
```

With Reflection:

```java
Class<?> c = Class.forName("Student");
Object obj = c.newInstance();
```

You didn’t write `new Student()` — Java created it at runtime.

---

# ✅ 2. Why Reflection is Needed?

Reflection is used when:

✔ You don’t know class name at compile time
✔ You want dynamic loading
✔ You are building frameworks
✔ You want plugin systems
✔ You want to analyze code

### Real Examples:

| Technology | Uses Reflection      |
| ---------- | -------------------- |
| Spring     | Dependency Injection |
| Hibernate  | ORM Mapping          |
| JUnit      | Test execution       |
| Android    | Runtime loading      |
| JVM        | Class loading        |

Without Reflection → Frameworks won’t work.

---

# ✅ 3. Reflection API Package

Java Reflection is in:

```
java.lang.reflect
```

Main Classes:

| Class       | Purpose                |
| ----------- | ---------------------- |
| Class       | Represents class       |
| Method      | Represents method      |
| Field       | Represents variable    |
| Constructor | Represents constructor |

---

# ✅ 4. Getting Class Object (Very Important)

To use Reflection, first get **Class object**.

### 3 Ways:

### 1️⃣ Using `.class`

```java
Class c = Student.class;
```

### 2️⃣ Using `getClass()`

```java
Student s = new Student();
Class c = s.getClass();
```

### 3️⃣ Using `Class.forName()`

```java
Class c = Class.forName("Student");
```

✔ Most used in frameworks.

---

# ✅ 5. Creating Object Using Reflection

Normal way:

```java
Student s = new Student();
```

Reflection way:

```java
Class c = Class.forName("Student");
Object obj = c.newInstance();
```

(New Way in Java 9+)

```java
Object obj = c.getDeclaredConstructor().newInstance();
```

⚠ `newInstance()` is deprecated.

---

# ✅ 6. Accessing Constructors

```java
Constructor[] cons = c.getConstructors();
```

Example:

```java
Constructor con = c.getConstructor(String.class);
Object obj = con.newInstance("Sumit");
```

---

# ✅ 7. Accessing Methods

### Get All Methods

```java
Method[] methods = c.getMethods();
```

### Get One Method

```java
Method m = c.getMethod("show");
```

### Call Method

```java
m.invoke(obj);
```

With Parameters:

```java
Method m = c.getMethod("add", int.class, int.class);
m.invoke(obj, 10, 20);
```

---

# ✅ 8. Accessing Fields (Variables)

### Get Fields

```java
Field[] fields = c.getDeclaredFields();
```

### Access Private Field

```java
Field f = c.getDeclaredField("name");
f.setAccessible(true);   // Breaks private
f.set(obj, "Sumit");
```

⚠ This bypasses encapsulation.

---

# ✅ 9. Accessing Private Members

By default:
❌ Private members not accessible.

Reflection:

```java
f.setAccessible(true);
```

This is called:

> **Breaking Encapsulation**

---

# ✅ 10. Annotations + Reflection

Reflection can read annotations.

Example:

```java
@Retention(RetentionPolicy.RUNTIME)
@interface Info {
    String author();
}
```

Usage:

```java
@Info(author="Sumit")
class Test {}
```

Read:

```java
Info i = c.getAnnotation(Info.class);
System.out.println(i.author());
```

Used in Spring, JPA.

---

# ✅ 11. Class Information

You can get:

```java
c.getName();        // class name
c.getSuperclass(); // parent
c.getInterfaces(); // interfaces
c.getModifiers();  // public/private
```

Example:

```java
System.out.println(Modifier.isPublic(c.getModifiers()));
```

---

# ✅ 12. Dynamic Class Loading

Load class at runtime:

```java
Class c = Class.forName("com.app.Driver");
```

Used in:

✔ JDBC
✔ Plugins
✔ Servers

Example JDBC:

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

---

# ✅ 13. Reflection in Frameworks (Real Flow)

### Spring Example:

1️⃣ Read config
2️⃣ Get class name
3️⃣ Load class
4️⃣ Create object
5️⃣ Inject dependencies

All using Reflection.

Without Reflection → Spring fails.

---

# ✅ 14. Performance Impact (Important in Exams)

Reflection is:

❌ Slower
❌ No compile-time checking
❌ Security risk

Why slow?

Because JVM skips optimizations.

---

# ✅ 15. Security Issues

Reflection can:

⚠ Access private data
⚠ Modify system classes
⚠ Break security

So Java has:

```java
SecurityManager
```

To restrict reflection.

---

# ✅ 16. Advantages

✔ Dynamic behavior
✔ Framework support
✔ Plugin systems
✔ Loose coupling

---

# ❌ 17. Disadvantages

✔ Slow
✔ Hard to debug
✔ Unsafe
✔ Breaks OOP rules

---

# ✅ 18. When NOT to Use Reflection

Don’t use when:

❌ Normal coding possible
❌ Performance critical
❌ Security important

Use only in frameworks.

---

# ✅ 19. Interview Level Example

### Complete Example

```java
class Student {
    private String name;

    public Student() {}

    public void show() {
        System.out.println("Hello " + name);
    }
}

public class Main {
    public static void main(String[] args) throws Exception {

        Class c = Class.forName("Student");

        Object obj = c.getDeclaredConstructor().newInstance();

        Field f = c.getDeclaredField("name");
        f.setAccessible(true);
        f.set(obj, "Sumit");

        Method m = c.getMethod("show");
        m.invoke(obj);
    }
}
```

Output:

```
Hello Sumit
```

---

# ✅ 20. Reflection vs Normal Code

| Feature     | Normal | Reflection |
| ----------- | ------ | ---------- |
| Speed       | Fast   | Slow       |
| Safety      | High   | Low        |
| Flexibility | Low    | High       |
| Usage       | Apps   | Frameworks |

---

# ✅ 21. C++ vs Java Reflection

| Feature      | Java | C++          |
| ------------ | ---- | ------------ |
| Reflection   | Yes  | No (Limited) |
| Runtime Info | Full | RTTI only    |
| Frameworks   | Many | Few          |

C++ has only `RTTI` (typeid, dynamic_cast).

---

# ✅ 22. Advanced: MethodHandle (Modern Way)

Java 7+:

```java
MethodHandles.Lookup lookup = MethodHandles.lookup();
MethodHandle mh = lookup.findVirtual(
    Student.class,
    "show",
    MethodType.methodType(void.class)
);

mh.invoke(obj);
```

✔ Faster than Reflection.

---

# ✅ 23. Summary (For Revision)

Reflection allows Java to:

✔ Inspect classes
✔ Create objects
✔ Call methods
✔ Access fields
✔ Read annotations
✔ Load classes dynamically

Used in:

✔ Spring
✔ Hibernate
✔ JUnit
✔ Android

But:

⚠ Slow + Unsafe

---


