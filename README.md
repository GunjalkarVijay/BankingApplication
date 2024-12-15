# BankingApplication

BankingApplication is a backend application designed to provide essential banking functionalities. It is built using **Java** and **Spring Boot** with **Maven** as the build tool. The application incorporates **JWT-based authentication**, implemented using a custom filter extending `OncePerRequestFilter`.

## Features

The application supports the following functionalities:

### Customer Features
- **User Registration**: Customers can register themselves into the application.
- **Account Creation**: Customers can create an account of their choice.
- **Nominee Management**: Customers can save a nominee for their respective account.
- **Card Details Retrieval**: Customers can retrieve details for the granted card.
- **Investment**: Customers can invest money through their bank account.

### Admin Features
- Admins have additional privileges (not explicitly mentioned here but can be extended as needed).

## User Roles
- **Customer**: Regular users of the banking system with access to customer-specific features.
- **Admin**: Users with administrative privileges to manage application resources and functionalities.

## Technologies Used
- **Java**: Core programming language.
- **Spring Boot**: Framework for building and running the application.
- **Maven**: Build and dependency management tool.
- **JPA**: For database connectivity and queries.
- **JWT (JSON Web Token)**: Used for secure authentication.
- **Custom Filter**: A `OncePerRequestFilter` implementation for handling JWT authentication.

## Prerequisites

To run the application, ensure you have the following installed:
- **Java 17** or later
- **Maven 3.8.0** or later
- **MySQL** (or another supported database)

## Getting Started

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd BankingApplication
   ```

2. **Configure Database**
   - Update the `application.yml` or `application.properties` file with your database credentials.

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**
   - The application will start on `http://localhost:8080` by default.

## API Endpoints

### UserController Endpoints

- **POST /user/login** (Body JwtRequest jwtRequest): Logs in the user after authenticating it through `authService` and assigns a JWT token.
- **POST /user/register** (Body UserDto user): Registers a new user and by default assigns it `ROLE_CUSTOMER`. The password is encoded using `BCryptPasswordEncoder`.

### UserAccountController Endpoints

- **POST /account/create/{userId}** (Body AccountDto accountDto, @PathVariable Long userId): Creates an account for the user with a unique account number. The account status will be set to "ACTIVE". Upon card assignment, the card number and CVV will be randomly generated. The allocation date will be the current date, and the expiry date will be set 5 years ahead.
- **GET /account/all/{userId}** (@PathVariable Long userId): Fetches the list of all accounts associated with the given user.
- **GET /account/balance** (@RequestParam Long accountNumber): Fetches the account balance for the given account number.
- **GET /account/nominee** (@RequestParam Long accountNumber): Fetches the nominee based on the given account number.
- **PUT /account/updateNominee/{accountId}** (@RequestBody NomineeDto nomineeDto, @PathVariable Long accountId): Updates the nominee for the given account ID.
- **GET /account/getKycDetails** (@RequestParam Long accountNumber): Fetches the user associated with the given account number. While returning the user, the `AccountList` and `InvestmentList` are set to null.
- **PUT /account/updateKyc/{accountId}** (@RequestBody KycDto kycDto, @PathVariable Long accountId): Updates the KYC details for the given account ID.
- **GET /account/getAccount/summary** (@RequestParam Long accountNumber): Fetches the account associated with the given account number. While returning the account, the user is set to null.

### UserCardController Endpoints

- **GET /card/block** (@RequestParam Long accountNumber, @RequestParam Long cardNumber): Blocks the card associated with the given account number. If the card is found, it returns "Card Blocked Successfully"; otherwise, it throws an exception with "No Card Found with the given cardNumber: {cardNumber}".
- **POST /card/apply/new** (@RequestParam Long accountNumber, @RequestBody CardDto cardDto): Applies for a new card. If the account already has a card linked, it throws an exception with "Account with number: {accountNumber} already has a card." Otherwise, it generates a new card.
- **PUT /card/setting** (@RequestBody Card card, @RequestParam Long cardNumber): Updates the card limit and PIN for the given card number based on card type.

### UserInvestmentController Endpoints

- **POST /invest/now** (@RequestParam Long accountId, @RequestBody InvestmentDto investmentDto): Creates an investment for the given account ID. If the account balance is insufficient, it throws an exception with "Error in Investment". Otherwise, it creates a new investment and returns "Investment successful".

### AdminController Endpoints

- **POST /admin/add** (Body AdminDto admin): Registers a new user and by default assigns it `ROLE_ADMIN`. The password is encoded using `BCryptPasswordEncoder`.
- **GET /admin/getAllUser**: Fetches the list of all users in the database.
- **GET /admin/getUserByName/{username}** (@PathVariable String username): Fetches a user by the given username.
- **DELETE /admin/deleteUser/{userId}** (@PathVariable Long userId): Deletes the user with the given user ID. If successful, it returns "Deleted Successfully"; otherwise, it returns "Error in deletion".
- **PUT /admin/account/deactivate** (@RequestParam Long userId, @RequestParam Long accountId): Deactivates the account with the given account ID for the specified user ID.
- **PUT /admin/account/activate** (@RequestParam Long userId, @RequestParam Long accountId): Activates the account with the given account ID for the specified user ID.
- **GET /admin/account/getActiveAccountsList**: Fetches the list of active accounts.
- **GET /admin/account/getInActiveAccountsList**: Fetches the list of inactive accounts.

## Security
- The application uses JWT for authentication and authorization.
- A custom filter ensures secure access to resources based on user roles.

## Contributing

Contributions are welcome! Please create a pull request or raise an issue for any bugs or feature requests.

## Contact
For any queries or support, please contact **Vijay Gunjalkar** at [vijaygunjalkara54@gmail.com](mailto:vijaygunjalkara54@gmail.com).

---

**Happy Banking!**
