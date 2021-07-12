# Overview
Welcome 👋!
We at [Appstyx](https://appstyx.com) are looking forward to hire new Android developers.

If you came across this page randomly we'd like to inform you that this repository contains an exercise that is meant to evaluate your skills: if you are interested in joining our team please have a look at our [job offer](https://androidjobs.io/jobs/android-developer-b6282a3a52) and drop us an email at `info AT appstyx DOT com` first.

Please complete the following tasks to help us better address your Android experience, we can't wait to have you onboard! We'd love to see the kinds of decisions you make given the existing code, nature of the app and time constraints (please don't spend more than 4 hours on it, time is precious).

## General instructions
- fork this repository, or clone it and push it to a private repository of yours, and start from the `develop` branch to apply your changes;
- complete as many tasks as you'd like but follow the requested order;
- use all the third-party dependencies you may need but elaborate on your choices;
- share the link to the forked repository, if public, or invite `@matteinn`, if private.

## Tasks
1. Add a new dropdown field below "Last Name" to let users pick their gender. The list of genders has to be fetched from the API.
2. Add validation on the signup screen: when clicking on the "Signup" button and any of the fields is empty please show an inline error like [this](docs/validation.jpg).
3. Perform the signup API call when clicking on the "Signup" button with all fields correctly set: in case of errors please show them inline, in case of success move to the Home screen and store the token for subsequent authenticated API calls.
4. Change the routing logic to redirect already authenticated users to Home, while unauthenticated ones should land on the Signup screen.
5. On the Home screen perform the API call to fetch user data and load that info on screen (avatar, first name, last name) like [this](docs/user.jpg).
6. Implement the "Logout" logic on the home page, redirecting the user to the signup screen afterwards and clearing the auth credentials.

## Notes
- all details about the APIs can be found [here](docs/API.md)

## Your thoughts
Added 3 new dependencies:
* [Coil](https://coil-kt.github.io/coil/getting_started/) for Image Loading
* [Retrofit](https://square.github.io/retrofit/) for Network Requests
* [Coroutines](https://developer.android.com/kotlin/coroutines/) to perform long running processes away from the main thread 
#### Other Comments
* I had a challenge with the routing and navigation using the MainViewModel, and I had to use the MainActivity to change the destination event from child fragments
* I would have created a BaseFragment and a BaseViewModel that communicate and manage loading states.