This project was updated to target Java 21 (LTS).

What I added:
- `pom.xml` configured with `<maven.compiler.release>21</maven.compiler.release>` to compile for Java 21.

Manual steps to complete the upgrade on Windows (PowerShell):

1) Install a JDK 21 distribution (Eclipse Temurin / Adoptium is recommended).
   - Download: https://adoptium.net/ or https://www.oracle.com/java/technologies/downloads/
   - Install normally.

2) Set `JAVA_HOME` and add to `PATH` (PowerShell commands):

```powershell
# Example (adjust path to your JDK install folder):
$jdkPath = 'C:\Program Files\Eclipse Adoptium\jdk-21.0.0.XXXXXXXX' # update this
[System.Environment]::SetEnvironmentVariable('JAVA_HOME', $jdkPath, 'User')
$env:Path = $env:Path + ';' + "$jdkPath\bin"
# To make PATH persistent for future PowerShell sessions (set User PATH):
$old = [System.Environment]::GetEnvironmentVariable('Path', 'User')
[System.Environment]::SetEnvironmentVariable('Path', $old + ';' + "$jdkPath\bin", 'User')
```

After setting environment variables, open a new PowerShell window and verify:

```powershell
java -version
javac -version
```

You should see Java 21 reported.

3) Build with Maven (requires Maven installed). If you don't have Maven, you can install it or use the `mvnw` wrapper (not added here).

```powershell
# If Maven installed
mvn -v
mvn -DskipTests package
```

This will compile all `.java` files and produce a JAR in `target/`.

Notes & next steps I can help with:
- Add a `mvnw` wrapper so contributors don't need Maven installed.
- Convert project to Gradle (if you prefer Gradle).
- Run or add unit tests and CI workflow to build with Java 21.
- Apply automated code migrations (OpenRewrite) — requires Copilot Pro/Enterprise or running OpenRewrite locally.

If you want, I can add a `mvnw` wrapper and a minimal `src/main/java` layout and a sample `Main` class, or convert the repo to a proper Maven structure.