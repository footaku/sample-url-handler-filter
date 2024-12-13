# See how UrlHandlerFilter treats trailing slashes.

- Java 21
- Spring Boot 3.4.0

## No Configurations

| endpoint with slash | access with slash | Response | note |
|:---:|:---:|:---:| --- |
| ◯ | ◯ | 200 | /slash/with/ <- curl /slash/with/ |
| ◯ | ☓ | 404 | /slash/with/ <- curl /slash/with |
| ☓ | ◯ | 404 | /slash/without <- curl /slash/without/ |
| ☓ | ☓ | 200 | /slash/without <- curl /slash/without |

## PathMatchConfigurer

| endpoint with slash | access with slash | Response | note |
|:---:|:---:|:---:| --- |
| ◯ | ◯ | 200 | /slash/with/ <- curl /slash/with/ |
| ◯ | ☓ | 404 | /slash/with/ <- curl /slash/with |
| ☓ | ◯ | 200 | /slash/without <- curl /slash/without/ |
| ☓ | ☓ | 200 | /slash/without <- curl /slash/without |

## UrlHandlerFilter

| endpoint with slash | access with slash | Response | note |
|:---:|:---:|:---:| --- |
| ◯ | ◯ | 404 | /slash/with/ <- curl /slash/with/ |
| ◯ | ☓ | 404 | /slash/with/ <- curl /slash/with |
| ☓ | ◯ | 200 | /slash/without <- curl /slash/without/ |
| ☓ | ☓ | 200 | /slash/without <- curl /slash/without |
