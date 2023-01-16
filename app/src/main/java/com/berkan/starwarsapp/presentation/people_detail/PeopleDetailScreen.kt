package com.berkan.starwarsapp.presentation.people_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.berkan.starwarsapp.domain.model.toPerson

@Composable
fun PeopleDetailScreen(
    personJson: String?
) {
    personJson?.toPerson()?.let { person ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = person.name,
                fontSize = 38.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            PersonCharacteristic(attribute = "Birth year", value = person.birthYear)

            Spacer(modifier = Modifier.height(28.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PersonCharacteristic(attribute = "Eye color", value = person.eyeColor)
                PersonCharacteristic(attribute = "Skin color", value = person.skinColor)
                PersonCharacteristic(attribute = "Hair color", value = person.hairColor)
            }
        }
    }

}

@Composable
fun PersonCharacteristic(
    attribute: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = attribute,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Light)
    }
}

@Preview(showBackground = true)
@Composable
fun CharacteristicPreview() {
    PersonCharacteristic(attribute = "Hair color", value = "Black")
}