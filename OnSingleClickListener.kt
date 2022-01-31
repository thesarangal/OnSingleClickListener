import android.view.View
import java.util.*

/**
 * View Extension Function to prevent double click
 *
 * @author Rajat Sarangal
 * @since December 31, 2021
 * @link https://github.com/thesarangal/OnSingleClickListener
 * */

// Minimum Interval Time Between Next Click
const val MIN_CLICK_INTERVAL_IN_MILLI = 500

// Time of last click
var lastUserClickOn = 0L

fun View.setOnSingleClickListener(listener: View.OnClickListener?) {

    // Register Generic Callback when this view is clicked.
    setOnClickListener {

        // Check is user click before safety interval
        if(Date().time - lastUserClickOn < MIN_CLICK_INTERVAL_IN_MILLI){
            return@setOnClickListener
        }

        // Store User's Last Click
        lastUserClickOn = Date().time

        // Invoke Callback
        listener?.onClick(it)
    }

}
