# AmazingTalker Assessment

## Abstract
To get started learning Kotlin. Only developed Android with Kotlin within one week.

Get back the coding and programming power by the assessment since the recent two years I am doing too many management tasks.

It is time to retrain myself and understand the modern language now.

Make the sword sharp again !!!

## Timeline
- 2022/05/07
  - Support localization
    - [Localize your app](https://developer.android.com/guide/topics/resources/localization)
    - [Locale.LanguageRange](https://developer.android.com/reference/java/util/Locale.LanguageRange)
      - [AOSP example](https://android.googlesource.com/platform/packages/apps/Settings/+/master/res/)
- 2022/05/06
  - [Date and Time on the Internet: Timestamps](https://www.ietf.org/rfc/rfc3339.txt) 
    - Date Parsing: [SimpleDateFormat(“Z”) in Java](https://www.tutorialspoint.com/simpledateformat-z-in-java)
    - The difference between Date() and LocalDate()
    - Practice Collections 
      - filter
      - sortedWith and compareBy
    - [Lambda expressions and anonymous functions are function literals](https://kotlinlang.org/docs/lambdas.html#lambda-expressions-and-anonymous-functions)
    - [View Binding](https://developer.android.com/topic/libraries/view-binding)
    - [Material Button](https://material.io/components/buttons)
- 2022/05/05
  - To download the https://github.com/android/views-widgets-samples.git and get some inspiration from it
  - To study the difference between ViewPager and ViewPager2 since last time I use the ViewPager is before 2019 year, it is time to upgrade my layout weapon.
    - [學不動也要學！深入瞭解ViewPager2](https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/712473/)
    - [Create swipe views with tabs using ViewPager2](https://developer.android.com/guide/navigation/navigation-swipe-view-2)
    - [Layout Inspector](https://developer.android.com/studio/debug/layout-inspector)
      - [turning-off-live-updates-by-default-in-layout-inspector](https://stackoverflow.com/questions/64342087/turning-off-live-updates-by-default-in-layout-inspector)
  - To learn the Network APIs by this [Volley](https://google.github.io/volley/)
  - To send e-mail tell AmazingTalker the APIs sample is wrong in the assignment, verify it by Chrome's debug mode to find out the request API url is different from the assignment.
  - Apply Gson to project
- 2022/05/04 at 15:20, receive the exam and should finish it by Kotlin, but I do not develop Kotlin over 3 days yet.
  - 2022/05/04 create the project and give simple layout
  - I should complete the assessment in three days, aka 72 hours, The deadline is on 2022/05/07 at 15:20

## Requirements

### :white_check_mark: Review the calendar of tutor
- Please show it by week unit
- You can not look the past information.
 
### :white_check_mark: To show the local time zone after you switching the system setting 
- The calendar will show the available and booked time according to the time zone

### :hammer: To show available and booked times 
- To get the time data from backend API, and split the time to small pieice by unit of one half hour
- To show grey color in booked time
- To show green color in available time

### :white_check_mark: Backend API of time data
- URL with two parameters, "tutor name"(julia-shin) and "start time"(2022-05-07T08:51:32Z).
  - i.e. https://en.amazingtalker.com/v1/guest/teachers/julia-shin/schedule?started_at=2022-05-07T08:51:32Z

### :white_check_mark: To support localization, English and tranditional Chinese
### :hammer: Better have
- Unit & UI testing codes

## Reference
- https://github.com/android
- https://github.com/googlesamples
