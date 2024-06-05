import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.hw2.Affirmation

data class Affirmation(
    @StringRes val stringResourceId: Int,
    @StringRes val stringResourceIdSecond: Int,
    @DrawableRes val imageResourceId: Int
)