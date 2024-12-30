# Two-Factor Authentication (2FA) Android Application

This project implements a Two-Factor Authentication (2FA) system for Android using Java. The app provides secure user authentication through email-based OTP (One-Time Password) verification during registration and login.

## Features
- **User Registration**: Users can register with their name, email, and a secure password.
- **Email Verification**: A verification code is sent to the user's email for account verification.
- **Secure Login**: Users log in with credentials and verify via OTP.
- **OTP Resend**: Users can request to resend the OTP code if required.
- **Password Security**: Passwords are securely hashed using BCrypt before being stored.
- **Session Management**: Session data is managed internally via a `Session` class.

## Project Structure
Here’s an overview of the project files and directories:

### **Key Directories**
- **`java/com.example.a19dhjetor2024`**: Contains all the core logic files.
  - **`DB.java`**: Manages database operations such as user registration, OTP storage, and verification.
  - **`EmailSender.java`**: Handles sending OTPs to users via email using the JavaMail API.
  - **`Login.java`**: Manages user login and OTP verification.
  - **`MainActivity.java`**: Entry point of the application.
  - **`Session.java`**: Manages user session states and stores the logged-in user's email.
  - **`signUp.java`**: Handles user registration and initial OTP verification.
- **`res/layout`**: Contains XML files for UI layouts.
  - **`activity_login.xml`**: Layout for the login screen.
  - **`activity_main.xml`**: Layout for the main screen.
  - **`activity_sign_up.xml`**: Layout for the sign-up screen.
- **`res/values`**: Contains color themes, string resources, and styles.
- **`AndroidManifest.xml`**: Defines app-level configurations, activities, and permissions.

## Technologies Used
- **Android Studio**: Used for development and testing.
- **Java**: The primary programming language.
- **SQLite**: For local data storage (e.g., user credentials and OTPs).
- **JavaMail API**: For sending OTPs via email.
- **BCrypt**: For secure password hashing.

## How It Works
### **Registration**
1. Users register by entering their name, email, and a strong password.
2. The app generates an OTP and sends it to the user’s email for verification.
3. Users input the OTP in the app to complete registration.

### **Login**
1. Users log in with their email and password.
2. If credentials are valid, an OTP is sent to their email.
3. Users input the OTP to access the application.

### **OTP Resend**
- If the OTP email is not received, users can request to resend the OTP.

## Setup Instructions
1. Clone the repository:
git clone https://github.com/aulonalivoreka/2FA-Implementation.git
2. Open the project in Android Studio.
3. Configure the email settings in `EmailSender.java`:
- Replace the `sendermail` and `senderpassword` with valid email credentials (use App Passwords for Gmail accounts).
4. Build and run the app on an Android device or emulator.
5. Test the registration and login functionalities to ensure OTP emails are sent and verified correctly.

## Requirements
- **Android Studio Bumblebee** or later.
- A valid Gmail account with App Passwords enabled.
- A physical or virtual Android device running Android 8.0 (API 26) or later.

## Security Measures
- **BCrypt**: Passwords are hashed for secure storage.
- **Email Verification**: Ensures the user has access to the provided email address.

## License
This project is licensed under the MIT License. Feel free to use and modify it as needed.




