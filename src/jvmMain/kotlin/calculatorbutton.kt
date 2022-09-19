
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp

@Composable
fun calculatorButton (symbol: String,height: Int = 50, width: Int = 55,backgroundColor:Color = Color(245, 245, 255) ,onButtonClick: () -> Unit){
    val shape = RoundedCornerShape(4.dp)
    Button(onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(backgroundColor =backgroundColor),
        modifier = Modifier.height(height.dp).width(width.dp).padding(all = 2.dp)
            .border(1.dp, Color(14, 14, 80), shape).shadow(elevation = 1.dp)  ){
        Text(symbol)
    }



}
