package com.example.app3

import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app3.Data.DataForm
import com.example.app3.Data.DataSource.jenis
import com.example.app3.ui.theme.App3Theme
import com.example.app3.ui.theme.CobaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
TampilLayout()
                }
            }
        }
    }
}

@Composable
fun TampilLayout(
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp){
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ){
                TampilForm()
            }


        }
    )
}

@Composable
fun TextHasil(namanya : String, telponnya : String, jenisnya : String ){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ){
       Text(
           text = "Nama : "+namanya,
           modifier = Modifier
               .padding(horizontal = 10.dp, vertical = 4.dp)
       )
        Text(text = "Telepon :" + telponnya,
        modifier = Modifier
            .padding(horizontal =10.dp, vertical = 4.dp ))

        Text(text = "Jenis Kelamin :" + jenisnya,
            modifier = Modifier
                .padding(horizontal =10.dp, vertical = 4.dp ))
    }
}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectionChanged: (String) -> Unit ={}
){
    var selectedValue by rememberSaveable{ mutableStateOf("") }

    Column(modifier = Modifier.padding(10.dp)) {
        options.forEach{ item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue== item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick={
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }

        }
    }
}

@Composable
fun TampilForm(cobaViewModel: CobaViewModel = viewModel()){

    var textNama by remember { mutableStateOf("")}
    var textTlp by remember { mutableStateOf("")}

    val context = LocalContext.current
    val dataForm: DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm=uiState;

    OutlinedTextField(
        value = textNama,
        singleLine = true,
        shape = MaterialTheme,shapes,large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Nama")},
        onValueChange = {
            textNama = it
        }

    )
    OutlinedTextField(
        value = textTlp,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme,shapes,large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Nama")},
        onValueChange = {
            textTlp = it
        }
    )
    SelectJK(options = jenis.map { id-> context.resources.getString(id)},
        onSelectionChanged = {cobaViewModel.setJenisK(it)})
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick ={
            cobaViewModel.insertData(textNama,textTlp,dataForm.sex)
        } ) {
        Text(
            text = stringResource(id = R.string.submit),
        fontSize = 16.sp
        )
    }
    Spacer(modifier = Modifier.height((100.dp))
    TextHasil(
            namanya = cobaViewModel.namaUsr,
            telponnya = cobaViewModel.noTlp,
            jenisnya = cobaViewModel.jenisKl
    )


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App3Theme {
TampilLayout()
    }
}