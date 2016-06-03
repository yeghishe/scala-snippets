package io.github.yeghishe.scalasnippets.scalazsnippets.higerlevel

/**
  * Created by yeghishe on 11/20/15.
  */
class WriterTSnippets {

  /*
  import scalaz.std.scalaFuture._
    import scalaz.syntax.std.boolean._
    import scalaz.syntax.nel._
    import scalaz.WriterT

    val r = WriterT.put(eventClient.handleCallStarted(callId, from, to))(
      s"Got a call, callId: $callId: from: $from, to: $to".wrapNel
    ).flatMap {
      _.fold(WriterT.put(catapultApiClient.playOutOfServiceMessage(callId))(
        s"Business service decided not to transfer the call or out of service call $callId.".wrapNel
      )) { call ⇒
        (call.isInboundCall && call.isVoicemailRedirected).fold(
          WriterT.put(sendToVoiceMail(call.callId, voiceMailGreetingUrl = call.voiceMailGreetingUrl))(
            s"Got a call but redirect to voice mail callId: $callId: from: $from, to: $to".wrapNel
          ),
          WriterT.put(createBridge(call, from, to, enableAudio = !call.isInboundCall))(
            s"creating bridge $callId".wrapNel
          )
        )
      }
    } run

    r.foreach(_._1.foreach(logger.info))
    r.map(_._2)
   */
}
