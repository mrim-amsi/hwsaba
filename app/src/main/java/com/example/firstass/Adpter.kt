
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstass.MainActivity
import com.example.firstass.Model
import com.example.firstass.R
import kotlinx.android.synthetic.main.list_item.view.*

class Adpter(val context: Context, val list: List<Model>, val click: onClick) :
    RecyclerView.Adapter<Adpter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = list[position].name
        holder.phone?.text = list[position].phone
        holder.email?.text = list[position].email
        holder.deletBtn.setOnClickListener {
            click.onClickItem(list[position].id  , position)

        }
    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name = item.nameId
        val phone = item.phoneNumberId
        val email = item.emailId
        val deletBtn = item.deletBtn


    }
    interface onClick {
        fun onClickItem(id:String ,position: Int)

    }

}