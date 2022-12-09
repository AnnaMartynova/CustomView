package com.example.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customview.databinding.PartButtonsBinding

class BottomButtonsView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: PartButtonsBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_buttons, this, true)
        binding = PartButtonsBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, desStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.BottomButtonsView,
            defStyleAttr,
            desStyleRes
        )

        with(binding) {
            val positiveButtonText =
                typedArray.getString(R.styleable.BottomButtonsView_bottomPositiveButtonText)
            binding.positiveButton.text = positiveButtonText ?: "Ok"

            val negativeButtonText =
                typedArray.getString(R.styleable.BottomButtonsView_bottomNegativeButtonText)
            binding.negativeButton.text = negativeButtonText ?: "Cancel"

            val positiveButtonBgColor =
                typedArray.getColor(R.styleable.BottomButtonsView_bottomPositiveBackgroundColor, Color.BLACK)
            binding.positiveButton.backgroundTintList =
                ColorStateList.valueOf(positiveButtonBgColor)

            val negativeButtonBgColor =
                typedArray.getColor(R.styleable.BottomButtonsView_bottomNegativeBackgroundColor, Color.WHITE)
            binding.negativeButton.backgroundTintList =
                ColorStateList.valueOf(negativeButtonBgColor)

            val isProgressMode =
                typedArray.getBoolean(R.styleable.BottomButtonsView_bottomProgressMode, false)
            if (isProgressMode) {
                positiveButton.visibility = INVISIBLE
                negativeButton.visibility = INVISIBLE
                progress.visibility = VISIBLE
            }else{
                positiveButton.visibility = VISIBLE
                negativeButton.visibility = VISIBLE
                progress.visibility = GONE
            }
        }

        typedArray.recycle()
    }
}