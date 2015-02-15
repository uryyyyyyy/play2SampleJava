package domain.init

import play.Logger
import vos.Account


case class ScalaSample(id:Int, title:String)

object ScalaSample{
	def func() = {
		val account: Account = new Account("id", "name")
		Logger.info(s"scala dayo ${account.id}")
	}
}