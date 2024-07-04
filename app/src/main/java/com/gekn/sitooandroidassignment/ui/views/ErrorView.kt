package com.gekn.sitooandroidassignment.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gekn.sitooandroidassignment.R

@Composable
fun ErrorView(
    modifier: Modifier,
    icon: Int,
    message: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = icon),
            contentDescription = "Sad image",
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
        )
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(
        modifier = Modifier
            .padding(16.dp),
        icon = R.drawable.ic_wifi_off_24,
        message = stringResource(id = R.string.error_no_internet_connection),
    )
}