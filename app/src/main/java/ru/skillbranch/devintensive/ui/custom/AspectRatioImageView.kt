package ru.skillbranch.devintensive.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import ru.skillbranch.devintensive.R

@SuppressLint("AppCompatCustomView")
class AspectRatioImageView @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {


    companion object{
        private const val DEFAULT_ASPECT_RAT = 1.78f
    }
    private var aspectRatio = DEFAULT_ASPECT_RAT
    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
            aspectRatio = a.getFloat(
                R.styleable.AspectRatioImageView_aspectRatio,
                DEFAULT_ASPECT_RAT)
            a.recycle() //этот метод позваляет экономить ресурсы устройства
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val newHeight = (measuredWidth/aspectRatio).toInt()
        setMeasuredDimension(measuredWidth,newHeight)
    }
}