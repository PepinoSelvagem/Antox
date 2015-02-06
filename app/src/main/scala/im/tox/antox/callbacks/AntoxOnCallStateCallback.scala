package im.tox.antox.callbacks

import android.content.Context
import android.util.Log
import im.tox.antox.tox.{Methods, ToxSingleton}
import im.tox.antox.utils.AntoxFriend
import im.tox.tox4j.av.callbacks.CallStateCallback
import im.tox.tox4j.av.enums.ToxCallState
import im.tox.tox4j.exceptions.ToxException

class AntoxOnCallStateCallback(private var ctx: Context) extends CallStateCallback {

  override def callState(friendNumber: Int, callState: ToxCallState): Unit = {
    Log.d("OnAvCallbackCallback", "Received a callback from: " + friendNumber)
    val toxSingleton = ToxSingleton.getInstance
    val call = toxSingleton.toxAv.getCallList.get(friendNumber).get
    call.state = Some(callState)

    callState match {
      case ToxCallState.SENDING_AV =>
        //do nothing
      case ToxCallState.SENDING_A =>
        //do nothing
      case ToxCallState.SENDING_V => call.mute()
      case ToxCallState.NOT_SENDING => call.mute()
      case ToxCallState.PAUSED => call.mute()

      case ToxCallState.END =>
        toxSingleton.toxAv.getCallList.remove(friendNumber)
    }
/*
      Log.d("OnAvCallbackCallback", "Callback type: ON_INVITE")


        case ToxAvCallbackID.ON_START =>
          Log.d("OnAvCallbackCallback", "Callback type: ON_START")
          toxSingleton.tox.avPrepareTransmission(callID, 3, 40, false)
          Methods.avStart(callID, toxCodecSettings)

        case ToxAvCallbackID.ON_CANCEL => Log.d("OnAvCallbackCallback", "Callback type: ON_CANCEL")
        case ToxAvCallbackID.ON_REJECT => Log.d("OnAvCallbackCallback", "Callback type: ON_REJECT")
        case ToxAvCallbackID.ON_END =>
          Log.d("OnAvCallbackCallback", "Callback type: ON_END")
          Methods.avEnd(callID)

        case ToxAvCallbackID.ON_RINGING => Log.d("OnAvCallbackCallback", "Callback type: ON_RINGING")
        case ToxAvCallbackID.ON_STARTING => Log.d("OnAvCallbackCallback", "Callback type: ON_STARTING")
        case ToxAvCallbackID.ON_ENDING => Log.d("OnAvCallbackCallback", "Callback type: ON_ENDING")
        case ToxAvCallbackID.ON_REQUEST_TIMEOUT => Log.d("OnAvCallbackCallback", "Callback type: ON_REQUEST_TIMEOUT")
        case ToxAvCallbackID.ON_PEER_TIMEOUT => Log.d("OnAvCallbackCallback", "Callback type: ON_PEER_TIMEOUT")
        case ToxAvCallbackID.ON_MEDIA_CHANGE => Log.d("OnAvCallbackCallback", "Callback type: ON_MEDIA_CHANGE")
      } catch {
        case e: ToxException => e.printStackTrace
      } */
  }
}
