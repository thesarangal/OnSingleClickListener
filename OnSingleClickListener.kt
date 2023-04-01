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

fun View.setOnSingleClickListener(
    minClickIntervalInMillis: Long = 500,
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
