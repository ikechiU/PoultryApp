package com.ikechiu.poultryapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikechiu.poultryapp.ui.theme.PoultryAppThemeSettings
import com.ikechiu.poultryapp.util.isNumber
import com.ikechiu.poultryapp.util.preventNonNumerics

@Composable
fun ContentText(
    modifier: Modifier = Modifier,
    paddingSize: Int = 0,
    itemName: String,
    itemValue: String,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            modifier = modifier
                .weight(1f)
                .padding(paddingSize.dp),
            text = itemName,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = fontWeight
            )
        )

        Text(
            modifier = modifier
                .weight(1f)
                .padding(paddingSize.dp),
            textAlign = TextAlign.End,
            text = itemValue,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = fontWeight
            )
        )
    }
}


@Composable
fun ContentTextField(
    itemName: String,
    itemQty: String,
    onTextChange: (String) -> Unit,
) {
    val background = if(PoultryAppThemeSettings.isDarkThemeEnabled)
        MaterialTheme.colors.surface else MaterialTheme.colors.background

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.weight(1f),
            text = itemName
        )

        TextField(
            modifier = Modifier.weight(0.4f),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = background,
                cursorColor = MaterialTheme.colors.onSecondary,
                focusedIndicatorColor = MaterialTheme.colors.onSecondary
            ),
            singleLine = true,
            value = itemQty,
            onValueChange = { stringValue ->
                if (isNumber(stringValue)) {
                    onTextChange(stringValue)
                } else {
                    onTextChange(preventNonNumerics(stringValue))
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun RowWithText(
    modifier: Modifier = Modifier,
    itemName: String,
    itemValue: String,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Row(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth()
    ) {

        Text(
            modifier = modifier
                .weight(1f)
                .padding(4.dp),
            text = itemName,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = fontWeight
            )
        )

        Text(
            modifier = modifier
                .padding(4.dp),
            textAlign = TextAlign.End,
            text = itemValue,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = fontWeight
            )
        )
    }

}
