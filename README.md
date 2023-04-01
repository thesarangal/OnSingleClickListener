# OnSingleClickListener
OnSingleClickListener is an Android view extension function that prevents double click on a view by setting a minimum time interval between two clicks.

## How to use
To use the OnSingleClickListener extension function, first import the file into your project. Then, call the function on a view and pass in the desired minimum click interval and the click listener:

```bash
  myButton.setOnSingleClickListener(minClickIntervalInMillis, listener)
```

### Parameters
- minClickIntervalInMillis (optional): The minimum time interval between two clicks in milliseconds. The default value is 500 milliseconds.
- listener: The click listener to be invoked when the view is clicked.

### Examples

```bash
  myButton.setOnSingleClickListener(1000, listener)
```

Or

```bash
  myButton.setOnSingleClickListener(listener)
```

Or

```bash
  myButton.setOnSingleClickListener(1000) {
    // Do something on click
  }
```
In this example, the minimum click interval is set to 1000 milliseconds (1 second). If the user clicks the button again before the 1-second interval has passed, the click will be ignored.

## Contributions

Contributions to this library are welcome. If you find a bug or have a feature request,
please open an issue on the [GitHub repository](https://github.com/thesarangal/OnSingleClickListener).

## License

This library is released under the [MIT License](https://opensource.org/licenses/MIT).