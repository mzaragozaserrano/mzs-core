package com.mzaragozaserrano.presentationapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mzaragozaserrano.compose.composables.utils.Recycler
import com.mzaragozaserrano.presentationapp.ui.utils.createComponentList

@Composable
fun CategoryComponentScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoryComponentViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    state.category?.let { category ->
        Recycler(
            modifier = modifier,
            list = category.createComponentList()
        ) { component ->
            Card(
                modifier = Modifier.padding(all = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(id = component.nameId).uppercase(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    component.item()
                }
            }
        }
    }
}