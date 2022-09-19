// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.singleWindowApplication
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen() {

    val calculatorState = remember { calculatorState() }
    val buttonclick = calculatorState::onButtonClick
    var colsize by remember {mutableStateOf(IntSize.Zero)}
    var buttonwidth: Int
    var buttonheight: Int
    val digitcolors = Color(185, 185, 195)
    val requester = remember { FocusRequester() }
    val keymap = mapOf(Key.Enter to '=',Key.Backspace to '←',Key.Escape to 'C')
    var keypushed: Char?
    val validcharacters = setOf('0','1','2','3','4','5','6','7','8','9','0','←','(',')','+','-','*','/','=','C','√','^','.','π' )



    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(color = Color.LightGray)
            ) {


        Column(
            modifier = Modifier.fillMaxSize().padding(6.dp), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(5.dp), verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = calculatorState.readout,
                    fontSize = 30.sp,
                    modifier = Modifier.fillMaxWidth().padding(3.dp).background(color = Color.White).shadow(1.dp),
                    textAlign = TextAlign.End

                )
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(5.dp).onGloballyPositioned { coordinates ->
                    colsize = coordinates.size}, verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                buttonwidth = colsize.width/5
                buttonheight = colsize.height/5


                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start) {
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = "←",height=buttonheight, width = buttonwidth
                            , onButtonClick = { buttonclick('←') })

                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = "(",height=buttonheight, width = buttonwidth
                            , onButtonClick = { buttonclick('(') })

                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = ")",height=buttonheight, width = buttonwidth
                            , onButtonClick = { buttonclick(')') })

                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                            calculatorButton(symbol = "Clear",height=buttonheight, width = buttonwidth*2
                                ,onButtonClick = { buttonclick('C') })
                        }

                    }

                }

                Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically
                    , horizontalArrangement = Arrangement.Start) {
                    Column(

                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        calculatorButton(symbol = "7",height=buttonheight, width = buttonwidth
                            ,backgroundColor = digitcolors, onButtonClick = { buttonclick('7') })



                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = "8",height=buttonheight, width = buttonwidth
                            ,backgroundColor = digitcolors, onButtonClick = { buttonclick('8') })

                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = "9",height=buttonheight, width = buttonwidth
                            ,backgroundColor = digitcolors, onButtonClick = { buttonclick('9') })


                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        calculatorButton(symbol = "÷",height=buttonheight, width = buttonwidth
                            ,onButtonClick = { buttonclick('/') })


                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = "√",height=buttonheight, width = buttonwidth
                            ,onButtonClick = { buttonclick('√') })

                    }
                }


                Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically
                    ,horizontalArrangement = Arrangement.Start) {
                    Column(

                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        calculatorButton(symbol = "4",height=buttonheight, width = buttonwidth
                            ,backgroundColor = digitcolors, onButtonClick = { buttonclick('4') })


                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        calculatorButton(symbol = "5",height=buttonheight, width = buttonwidth
                            ,backgroundColor = digitcolors, onButtonClick = { buttonclick('5') })

                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        calculatorButton(symbol = "6",height=buttonheight, width = buttonwidth
                            ,backgroundColor = digitcolors, onButtonClick = { buttonclick('6') })

                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        calculatorButton(symbol = "x",height=buttonheight, width = buttonwidth
                            ,onButtonClick = { buttonclick('*') })

                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        calculatorButton(symbol = "^",height=buttonheight
                            ,width = buttonwidth, onButtonClick = { buttonclick('^') })
                    }
                }


                Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically
                    ,horizontalArrangement = Arrangement.Start) {
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = "1",height=buttonheight, width = buttonwidth
                            ,backgroundColor = digitcolors, onButtonClick = { buttonclick('1') })
                        calculatorButton(symbol = "0",height=buttonheight, width = buttonwidth
                            ,backgroundColor = digitcolors, onButtonClick = { buttonclick('0') })
                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = "2",height=buttonheight, width = buttonwidth
                            ,backgroundColor = digitcolors, onButtonClick = { buttonclick('2') })
                        calculatorButton(symbol = ".",height=buttonheight, width = buttonwidth
                            ,onButtonClick = { buttonclick('.') })
                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = "3",height=buttonheight, width = buttonwidth
                            ,backgroundColor = digitcolors, onButtonClick = { buttonclick('3') })
                        calculatorButton(symbol = "±",height=buttonheight, width = buttonwidth
                            ,onButtonClick = { buttonclick('±') })
                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = "-",height=buttonheight, width = buttonwidth
                            ,onButtonClick = { buttonclick('-') })
                        calculatorButton(symbol = "+",height=buttonheight, width = buttonwidth
                            ,onButtonClick = { buttonclick('+') })
                    }
                    Column(
                        modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        calculatorButton(symbol = "=",height=buttonheight*2, width = buttonwidth
                            ,backgroundColor = Color(58, 136, 252),  onButtonClick = { buttonclick('=') })
                    }
                }

            }

        }

    }


    Box(
        Modifier
            .onKeyEvent {
                if(it.type == KeyEventType.KeyDown) {
                    keypushed = getKeyChar(it.toString())
                    if(keypushed == null)
                        keypushed = keymap[it.key]
                    if(keypushed != null && validcharacters.contains(keypushed))
                        buttonclick(keypushed!!)
                }
                false
            }
            .focusRequester(requester)
            .focusable()
            .background(color = Color.Red)
    )
    LaunchedEffect(Unit) {
        requester.requestFocus()
    }


}



fun getKeyChar(keyEventstring: String) :Char?{
    var keyFound : Char? = null
    var found : String? = null

    val matchresult = "keyChar='(.*)'".toRegex().find(keyEventstring)
    if(matchresult?.value == null)
        keyFound = null
    else {
        found = (matchresult.groups[1]?.value)
        keyFound = found!![0]
    }
    return (keyFound)
}

fun main() = singleWindowApplication(state = WindowState(size = DpSize(380.dp, 355.dp)),title = "Calculator",) {
        MainScreen()
}
