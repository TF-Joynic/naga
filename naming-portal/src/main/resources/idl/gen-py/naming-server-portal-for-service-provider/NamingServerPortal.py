#
# Autogenerated by Thrift Compiler (0.9.3)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TException, TApplicationException
import logging
from ttypes import *
from thrift.Thrift import TProcessor
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol, TProtocol
try:
  from thrift.protocol import fastbinary
except:
  fastbinary = None


class Iface:
  def doRegister(self, namespace, protocolType, serviceName, host, port, weight):
    """
    Parameters:
     - namespace
     - protocolType
     - serviceName
     - host
     - port
     - weight
    """
    pass


class Client(Iface):
  def __init__(self, iprot, oprot=None):
    self._iprot = self._oprot = iprot
    if oprot is not None:
      self._oprot = oprot
    self._seqid = 0

  def doRegister(self, namespace, protocolType, serviceName, host, port, weight):
    """
    Parameters:
     - namespace
     - protocolType
     - serviceName
     - host
     - port
     - weight
    """
    self.send_doRegister(namespace, protocolType, serviceName, host, port, weight)
    return self.recv_doRegister()

  def send_doRegister(self, namespace, protocolType, serviceName, host, port, weight):
    self._oprot.writeMessageBegin('doRegister', TMessageType.CALL, self._seqid)
    args = doRegister_args()
    args.namespace = namespace
    args.protocolType = protocolType
    args.serviceName = serviceName
    args.host = host
    args.port = port
    args.weight = weight
    args.write(self._oprot)
    self._oprot.writeMessageEnd()
    self._oprot.trans.flush()

  def recv_doRegister(self):
    iprot = self._iprot
    (fname, mtype, rseqid) = iprot.readMessageBegin()
    if mtype == TMessageType.EXCEPTION:
      x = TApplicationException()
      x.read(iprot)
      iprot.readMessageEnd()
      raise x
    result = doRegister_result()
    result.read(iprot)
    iprot.readMessageEnd()
    if result.success is not None:
      return result.success
    raise TApplicationException(TApplicationException.MISSING_RESULT, "doRegister failed: unknown result")


class Processor(Iface, TProcessor):
  def __init__(self, handler):
    self._handler = handler
    self._processMap = {}
    self._processMap["doRegister"] = Processor.process_doRegister

  def process(self, iprot, oprot):
    (name, type, seqid) = iprot.readMessageBegin()
    if name not in self._processMap:
      iprot.skip(TType.STRUCT)
      iprot.readMessageEnd()
      x = TApplicationException(TApplicationException.UNKNOWN_METHOD, 'Unknown function %s' % (name))
      oprot.writeMessageBegin(name, TMessageType.EXCEPTION, seqid)
      x.write(oprot)
      oprot.writeMessageEnd()
      oprot.trans.flush()
      return
    else:
      self._processMap[name](self, seqid, iprot, oprot)
    return True

  def process_doRegister(self, seqid, iprot, oprot):
    args = doRegister_args()
    args.read(iprot)
    iprot.readMessageEnd()
    result = doRegister_result()
    try:
      result.success = self._handler.doRegister(args.namespace, args.protocolType, args.serviceName, args.host, args.port, args.weight)
      msg_type = TMessageType.REPLY
    except (TTransport.TTransportException, KeyboardInterrupt, SystemExit):
      raise
    except Exception as ex:
      msg_type = TMessageType.EXCEPTION
      logging.exception(ex)
      result = TApplicationException(TApplicationException.INTERNAL_ERROR, 'Internal error')
    oprot.writeMessageBegin("doRegister", msg_type, seqid)
    result.write(oprot)
    oprot.writeMessageEnd()
    oprot.trans.flush()


# HELPER FUNCTIONS AND STRUCTURES

class doRegister_args:
  """
  Attributes:
   - namespace
   - protocolType
   - serviceName
   - host
   - port
   - weight
  """

  thrift_spec = None
  def __init__(self, namespace=None, protocolType=None, serviceName=None, host=None, port=None, weight=None,):
    self.namespace = namespace
    self.protocolType = protocolType
    self.serviceName = serviceName
    self.host = host
    self.port = port
    self.weight = weight

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == -1:
        if ftype == TType.STRING:
          self.namespace = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == -2:
        if ftype == TType.STRING:
          self.protocolType = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == -3:
        if ftype == TType.STRING:
          self.serviceName = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == -4:
        if ftype == TType.STRING:
          self.host = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == -5:
        if ftype == TType.I32:
          self.port = iprot.readI32()
        else:
          iprot.skip(ftype)
      elif fid == -6:
        if ftype == TType.I32:
          self.weight = iprot.readI32()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('doRegister_args')
    if self.weight is not None:
      oprot.writeFieldBegin('weight', TType.I32, -6)
      oprot.writeI32(self.weight)
      oprot.writeFieldEnd()
    if self.port is not None:
      oprot.writeFieldBegin('port', TType.I32, -5)
      oprot.writeI32(self.port)
      oprot.writeFieldEnd()
    if self.host is not None:
      oprot.writeFieldBegin('host', TType.STRING, -4)
      oprot.writeString(self.host)
      oprot.writeFieldEnd()
    if self.serviceName is not None:
      oprot.writeFieldBegin('serviceName', TType.STRING, -3)
      oprot.writeString(self.serviceName)
      oprot.writeFieldEnd()
    if self.protocolType is not None:
      oprot.writeFieldBegin('protocolType', TType.STRING, -2)
      oprot.writeString(self.protocolType)
      oprot.writeFieldEnd()
    if self.namespace is not None:
      oprot.writeFieldBegin('namespace', TType.STRING, -1)
      oprot.writeString(self.namespace)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.namespace)
    value = (value * 31) ^ hash(self.protocolType)
    value = (value * 31) ^ hash(self.serviceName)
    value = (value * 31) ^ hash(self.host)
    value = (value * 31) ^ hash(self.port)
    value = (value * 31) ^ hash(self.weight)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class doRegister_result:
  """
  Attributes:
   - success
  """

  thrift_spec = (
    (0, TType.BOOL, 'success', None, None, ), # 0
  )

  def __init__(self, success=None,):
    self.success = success

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 0:
        if ftype == TType.BOOL:
          self.success = iprot.readBool()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('doRegister_result')
    if self.success is not None:
      oprot.writeFieldBegin('success', TType.BOOL, 0)
      oprot.writeBool(self.success)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.success)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)
