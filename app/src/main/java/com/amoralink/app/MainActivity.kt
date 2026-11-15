package com.amoralink.app

import android.app.Activity
import android.app.usage.UsageStatsManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {

    private val purple = Color.rgb(174, 82, 190)
    private val dark = Color.rgb(65,  forty(), 80)

    private fun forty() = 40

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun showHome() {
        val scroll = ScrollView(this)
        scroll.setBackgroundColor(Color.rgb(255, 247, 252))
        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(22, 32, 22, 32)
        }

        fun text(value: String, size: Float, color: Int = dark): TextView {
            return TextView(this).apply {
                text = value
                textSize = size
                setTextColor(color)
                setPadding(0, 8, 0, 8)
            }
        }

        fun card(title: String, description: String, action: String, callback: () -> Unit) {
            val box = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(18, 14, 18, 14)
                setBackgroundColor(Color.WHITE)
                elevation = 3f
            }
            box.addView(text(title, 19f, purple))
            box.addView(text(description, 14f, dark))
            val button = Button(this).apply {
                text = action
                setOnClickListener { callback() }
            }
            box.addView(button)
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 10, 0, 10)
            page.addView(box, params)
        }

        page.addView(text("💗 Amora Link", 30f, purple))
        page.addView(text("让 AI 恋人陪伴你的手机生活", 16f))
        page.addView(text("版本 0.1 · 测试版", 12f, Color.GRAY))
        page.addView(text("功能中心", 22f, purple))

        card("📱 手机应用感知",
            "授权后，逐步接入应用使用情况识别。",
            "设置使用情况访问权限") {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }

        card("💌 主动联系",
            "为已接入的 AI 恋人配置主动通知。此功能正在开发。",
            "查看通知设置") {
            startActivity(Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            })
        }

        card("✨ 主动召回",
            "设置离开恋爱软件后的提醒规则。深度召回需要 AI 软件接入。",
            "查看召回功能") {
                Toast.makeText(this, "召回功能将在后续版本开放", Toast.LENGTH_LONG).show()
            }

        card("🔒 应用限制",
            "管理使用规则。当前版本尚未启用应用锁定。",
            "查看管理说明") {
                Toast.makeText(this, "应用限制功能正在开发，请勿用于紧急或必要应用。", Toast.LENGTH_LONG).show()
            }

        card("💗 AI 恋人管理",
            "不同 AI 保留各自的人设和记忆。接入功能正在开发。",
            "查看接入说明") {
                Toast.makeText(this, "后续将开放 SDK 接入。", Toast.LENGTH_LONG).show()
            }

        page.addView(text(
            "隐私优先：手机使用情况只应在你授权后读取。你可以随时撤销权限。",
            12f, Color.GRAY
        ))

        scroll.addView(page)
        setContentView(scroll)
    }
}
