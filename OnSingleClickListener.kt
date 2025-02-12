
import android.view.View

/**
 * View Extension Function to prevent double click
 *
 * @author Rajat Sarangal
 * @since April 01, 2023
 * @link https://github.com/thesarangal/OnSingleClickListener
 * */

// Time of last click
var lastUserClickOn = 0L

// Default Minimum Click Internal Time
const val MIN_CLICK_INTERVAL = 500L

/**
 * Extension function for [View] to set a single click listener with a minimum click interval.
 * This prevents accidental double-clicks or rapid multiple clicks within a short time frame.
 *
 * @param minClickIntervalInMillis The minimum time interval (in milliseconds) between successive clicks that will be registered.
 *                                Clicks within this interval will be ignored. Defaults to [MIN_CLICK_INTERVAL].
 * @param listener The [View.OnClickListener] to be invoked when a valid single click occurs.
 *                 Can be null, in which case no action will be performed after the click is validated.
 *
 * Example Usage:
 * ```kotlin
 * myButton.setOnSingleClickListener {
 *     // Handle single click here
 *     println("Button clicked!")
 * }
 *
 * myButton.setOnSingleClickListener(500) {
 *     // Handle single click here with a custom interval of 500ms
 *     println("Button clicked with 500ms interval!")
 * }
 *
 * myButton.setOnSingleClickListener(listener = View.OnClickListener {
 *     println("Button clicked with listener object!")
 * })
 * ```
 *
 * Note: This function uses a shared static variable `lastUserClickOn` to track the last click time
 * across all views using this extension.  If you need per-view click tracking, you will want to
 * use a different solution that does not rely on a shared static variable.
 */
fun View.setOnSingleClickListener(
    minClickIntervalInMillis: Long = MIN_CLICK_INTERVAL,
    listener: View.OnClickListener?
) {

    // Register Generic Callback when this view is clicked.
    setOnClickListener {

        val currentTime = System.currentTimeMillis()

        // Check is user click before safety interval
        if (currentTime - lastUserClickOn < minClickIntervalInMillis) {
            return@setOnClickListener
        }

        // Store User's Last Click
        lastUserClickOn = currentTime

        // Invoke Callback
        listener?.onClick(it)
    }
}


/**
 * Creates a debounced single-click listener function.
 *
 * This function returns a lambda that can be used as a click listener.
 * It ensures that the `onClick` action is only performed once within a specified time interval
 * (defined by `minClickIntervalInMillis`). Subsequent clicks within this interval are ignored.
 * This is useful for preventing accidental double-clicks or rapid multiple clicks on UI elements.
 *
 * @param minClickIntervalInMillis The minimum time interval (in milliseconds) between two consecutive clicks
 *                                 for the `onClick` action to be executed. Defaults to `MIN_CLICK_INTERVAL` (500ms).
 * @param onClick The action to be performed when a single, valid click occurs.
 *
 * @return A lambda (function) that represents the debounced click listener.
 *         This lambda should be invoked when a click event occurs.
 *
 * Example Usage:
 *
 * ```kotlin
 * @Composable
 * fun SingleClickButtonExample() {
 *     Button(
 *         onClick = onSingleClick {
 *             // Your click action here
 *             println("Button clicked!")
 *         }
 *     ) {
 *         Text("Click Me")
 *     }
 * }
 * ```
 */
fun onSingleClick(
    minClickIntervalInMillis: Long = MIN_CLICK_INTERVAL,
    onClick: () -> Unit
): () -> Unit {
    return {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastUserClickOn >= minClickIntervalInMillis) {
            lastUserClickOn = currentTime
            onClick()
        }
    }
}
