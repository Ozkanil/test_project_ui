Feature: User login
 #Scenarios covered:
 # Positive:
 # 1) A newly registered user can login
 # 2) Existing user can login
 # 3) User can login even after putting white space after username and password
 # 4) User can view login credentials by entering correct personal info

 # Negative:
 # 1) User cannot login when wrong username and password are entered
 # 2) User cannot login when wrong username is entered
 # 3) User cannot login when wrong password is entered
 # 4) User cannot login when no username and password is entered
 # 5) User cannot view username and password when all personal info isn't provided
 # 6) User cannot view username and password when no personal info is provided

  Scenario: A newly registered user can login
    Given a user open the home page
    And the user makes registration
    And the registration is successful
    When the user enters "new" username and password
    Then the user logged in successfully

  Scenario Outline: An existing user can login  with or without white spaces in the username and password
    Given a user open the home page
    When the user enters "<credentialState>" username and password
    Then the user logged in successfully

    Examples:
    | credentialState   |
    | existing          |
    | white spaces with |

  Scenario Outline: A user cannot login with a wrong username and password or empty credentials
    Given a user open the home page
    When the user enters "<credentialState>" username and password
    Then the user is shown "<errorMessage>"

    Examples:
      | credentialState | errorMessage                                     |
      | invalid         | The username and password could not be verified. |
      | no              | Please enter a username and password.            |

  Scenario Outline: A user cannot login with a wrong username or wrong password
    Given a user open the home page
    When the user enters "<credentialState>"
    Then the user is shown "<errorMessage>"

    Examples:
      | credentialState | errorMessage                                     |
      | wrong username  | The username and password could not be verified. |
      | wrong password  | The username and password could not be verified. |

  Scenario: A registered user can access forgotten login info in customer lookup page
    Given a user open the home page
    And the user makes registration
    And the registration is successful
    And the user searches forgotten user credentials "with valid data"
    When the user enters "saved" username and password
    Then the user logged in successfully

  Scenario Outline: A registered user cannot access forgotten login credentials without providing all required info
    Given a user open the home page
    And the user makes registration
    And the registration is successful
    When the user searches forgotten user credentials "with missing data"
    Then the user is  shown "<errorMessage>"

    Examples:
    | errorMessage                       |
    | Social Security Number is required.|

  Scenario: A registered user cannot access forgotten login credentials without providing any info
    Given a user open the home page
    When the user searches forgotten user credentials without providing any personal info
    Then the user is shown the proper error messages