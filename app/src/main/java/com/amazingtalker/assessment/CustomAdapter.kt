import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.amazingtalker.assessment.DateUtility
import com.amazingtalker.assessment.ItemsViewModel
import com.amazingtalker.assessment.R
import java.text.SimpleDateFormat
import java.util.*

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEWTYPE_TOP = 0x01
    private val VIEWTYPE_NORMAL = 0x02
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            VIEWTYPE_TOP -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_week_selector, parent, false)
                return WeekSelectorViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_design, parent, false)
                return DesignViewHolder(view)
            }
        }
    }

    // binds the list items to a view
    override fun onBindViewHolder(@Nullable holder:  RecyclerView.ViewHolder, position: Int) {
        if (holder is DesignViewHolder) {
            val ItemsViewModel = mList[position]

            // sets the image to the imageview from our itemHolder class
            holder.imageView.setImageResource(ItemsViewModel.image)

            // sets the text to the textview from our itemHolder class
            holder.textView.text = ItemsViewModel.text
        } else if (holder is WeekSelectorViewHolder) {
            holder.textView.text = DateUtility.getDateString().plus(" ~ ").plus(DateUtility.getDateString(6))
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEWTYPE_TOP else VIEWTYPE_NORMAL
    }

    class DesignViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
    class WeekSelectorViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.weekTitle)
    }
}
