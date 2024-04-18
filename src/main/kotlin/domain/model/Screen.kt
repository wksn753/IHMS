package domain.model

import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Star

enum class Screen(
    val label: String,
    val icon: ImageVector
) {
    HomeScreen(
        label = "Home",
        icon = Icons.Filled.Home
    ),
    PatientScreen(
        label = "Patients",
        icon = Icons.Filled.Face
    ),
    UserScreen(
        label = "User",
        icon = Icons.Filled.AccountCircle
    ),
    MessageScreen(
        label = "Messages",
        icon = Icons.Outlined.MailOutline
    ),
    BillingAndRecords(
        label = "Billing And Records",
        icon = Icons.Filled.Create
    ),
    InsuranceScreen(
        label="Insurance",
        icon = Icons.Default.Star
    )

}
