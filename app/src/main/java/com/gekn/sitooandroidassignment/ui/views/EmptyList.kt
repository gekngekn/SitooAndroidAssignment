package com.gekn.sitooandroidassignment.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gekn.sitooandroidassignment.R

@Composable
fun EmptyList(
    onRetry: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.ic_sad),
            contentDescription = "Sad image",
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
        )
        Text(
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp
            ),
            text = LocalContext.current.getString(R.string.error_no_content_found),
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedButton(
            modifier = Modifier,
            onClick = { onRetry() }
        ) {
            Text(
                text = stringResource(id = R.string.retry)
            )
        }
    }
}

@Preview
@Composable
fun EmptyListPreview() {
    EmptyList()
}