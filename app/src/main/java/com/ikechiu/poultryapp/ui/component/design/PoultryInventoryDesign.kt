import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ikechiu.poultryapp.R

@Composable
fun PoultryInventoryDesign(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp)
        ) {
            ColumnImage(modifier.weight(1f), painter = painterResource(id = R.drawable.bird))
            Column(
                modifier.weight(3f)
                    .padding(start = 2.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Header(header = "Bird Count")
                RowText(textOne = "Chicks", textTwo = "126")
                RowText(textOne = "Hen", textTwo = "120")
                RowText(textOne = "Cockerels", textTwo = "10")
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp)
        ) {
            Column(
                modifier
                    .padding(start = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Header(header = "Mortality")
                RowText(textOne = "Mortality Rate", textTwo = "1")
            }
        }


        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp)
        ) {
            ColumnImage(modifier.weight(1f), painter = painterResource(id = R.drawable.feed))
            Column(
                modifier.weight(3f)
                    .padding(start = 2.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Header(header = "Daily Feed")
                RowText(textOne = "Combo", textTwo = "126")
                RowText(textOne = "Cracked Corn", textTwo = "120")
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp)
        ) {
            ColumnImage(modifier.weight(1f), painter = painterResource(id = R.drawable.egg))
            Column(
                modifier.weight(3f)
                    .padding(start = 2.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Header(header = "Eggs")
                RowText(textOne = "Standard Brown", textTwo = "126")
                RowText(textOne = "Organic", textTwo = "120")
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp)
        ) {
            Column(
                modifier
                    .padding(start = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Header(header = "Water")
                RowText(textOne = "Water Consumption", textTwo = "1")
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp)
        ) {
            ColumnImage(modifier.weight(1f), painter = painterResource(id = R.drawable.drug))
            Column(
                modifier.weight(3f)
                    .padding(start = 2.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Header(header = "Drugs")
                RowText(textOne = "Antibiotics", textTwo = "126")
                RowText(textOne = "Regular Drugs", textTwo = "120")
            }
        }
    }
}

@Composable
fun ColumnImage(
    modifier: Modifier = Modifier,
    painter: Painter? = null
) {
    Column(
        modifier = modifier
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        if (painter != null)
            Image(
                painter = painter,
                contentDescription = "Image description",
                alignment = Alignment.TopStart
            )
    }
}

@Composable
private fun Header(header: String) {
    Text(
        text = header,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
private fun RowText(modifier: Modifier = Modifier, textOne: String, textTwo: String) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            text = "$textOne: ",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        )

        Text(
            text = textTwo,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

