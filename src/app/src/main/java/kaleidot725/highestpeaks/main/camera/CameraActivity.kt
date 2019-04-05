package kaleidot725.highestpeaks.main.camera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import kaleidot725.highestpeaks.R

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        supportFragmentManager.beginTransaction().replace(R.id.camera_content, CameraFragment.newInstance()).commit()
    }
}
