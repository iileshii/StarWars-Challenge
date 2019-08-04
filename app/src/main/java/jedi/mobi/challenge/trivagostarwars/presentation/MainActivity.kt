package jedi.mobi.challenge.trivagostarwars.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jedi.mobi.challenge.trivagostarwars.R

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            Intent(context, MainActivity::class.java)
                .run(context::startActivity)

            (context as? Activity)
                ?.run {
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val id = 1L //todo get id by click on

        showCharacter(id) //todo
    }

    private fun showCharacter(id: Long) {
        with(supportFragmentManager.beginTransaction()) {
            add(R.id.container, CharacterFragment.newInstance(id), CharacterFragment.TAG)
            commit()
        }
    }

}