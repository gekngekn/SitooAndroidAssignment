package com.gekn.sitooandroidassignment.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gekn.sitooandroidassignment.domain.PrimitiveExtensions.capitalizeAndBlankStepWords

@Composable
fun ProductDetailItem(
    field: String,
    value: Any
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = field.capitalizeAndBlankStepWords() + ":",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = value.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun ProductDetailItemPreview() {
    ProductDetailItem(
        field = "fieldValueIsThis",
        value = "true"
    )
}