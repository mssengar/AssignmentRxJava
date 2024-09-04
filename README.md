Built by using: 

1.	Clean Architecture -> It provide separation of concern & ease of  scalability, performance, security, code reduction etc.
2.	RxJava -> For handling Asynchronous event and data stream in more concise w& declarative way.
3.	Hilt -> For Dependency Injection.
4.	MVVM [Model-View-ViewModel] -> MVVM separate an application's business logic and UI. Loosely coupled, increasing the testability.
5.	View Binding -> It replaces findViewById making it easier to code that interacts with views. Enable it by added "viewBindind = true" in build gradle file.
6.	LiveData -> It's an Observable data holder. Works on Observer Pattern and updates the view whenever there is change in data. Also, Activity lifecycle aware.
7.	Navigation -> We used Jetpack Navigation component. Provides a consistent way to manage the Activity/Fragment. Uses visual graph to make the entire process easier, clearer and more consistent.
8.	ViewModel -> It's a class that holds the state of UI. Encapsulate the business Logic and is Lifecycle aware.

This sample can be improved by implementing :
1.	Unit testcases (JUnit, Mockito)
2.	CI/CD (Continuous Integration/ Continuous Delivery) pipeline. CI -> Automatically build and test the app when changes are pushed, ensuring code quality and early bug detection. CD -> Automate app deployment to different environments (e.g., testing, production) for consistent and reliable releases.
3.	UI Testcases (Espresso)
