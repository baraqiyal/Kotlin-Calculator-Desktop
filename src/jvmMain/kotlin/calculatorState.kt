import androidx.compose.runtime.*
import com.notkamui.keval.Keval
import com.notkamui.keval.KevalInvalidExpressionException
import com.notkamui.keval.KevalZeroDivisionException


class calculatorState() {

    var readout: String by  mutableStateOf("0")



    fun onButtonClick(buttonpressed: Char){
        var result: String

        readout = if(readout[0].isLetter()) "0" else readout

        when(buttonpressed){
            '=' -> readout = doCalculation(readout)
            'C' -> readout = "0"
            '±' -> readout = if(readout[0] == '-') readout.drop(1) else "-$readout"
            '√' -> {
                readout = Math.sqrt(doCalculation(readout).toDouble()).toString()
                readout = if (readout.endsWith(".0")) readout.dropLast(2) else readout
            }
            '←' -> {
                readout = readout.dropLast(1)
                readout = if(readout.isEmpty()) "0" else readout
            }
            'π' -> readout = readout + "PI"
            else -> {
                readout += buttonpressed
                readout = if (readout.length > 2 && readout.startsWith("-0")) ("-" + readout.drop(2)) else readout
                readout = if (readout[0] == '0' && readout.length > 1)  readout.drop(1) else readout
            }

        }

    }

    fun doCalculation(readout: String) : String{

        var equation: String

        equation = readout.replace("π","3.1415926")

        equation = if (equation[0] == '-') "0$equation" else equation

        try {
            equation = Keval.eval(equation).toString()
            equation = if(equation.endsWith(".0")) equation.dropLast(2) else equation
            equation = if (equation[0] == '0' && equation.length > 1)  equation.drop(1) else equation
        } catch (e: Exception) {
            when(e) {
                is KevalZeroDivisionException -> equation = "div by 0 error"
                else -> equation = "invalid expression"
            }
        }
        return equation
    }

}