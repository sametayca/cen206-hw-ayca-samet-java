@echo off
echo.
echo 1 - Start Console Application
echo 2 - Start Graphical User Interface (GUI) Application
set /p choice=Enter your choice (1/2): 

if "%choice%"=="1" (
    cls
    REM Start the console application (change main class if needed)
    java -jar tracker-app/target/tracker-app-1.0-SNAPSHOT.jar
    echo Operation Completed!
    pause
    exit /b
)

if "%choice%"=="2" (
    cls
    cd tracker-app
    REM call mvn clean package
    java -cp target/tracker-app-1.0-SNAPSHOT.jar com.samet.erdem.tracker.gui.UserAuthFrame
    pause
    exit /b
)

echo Invalid choice!
pause