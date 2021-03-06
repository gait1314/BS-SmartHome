#ifndef _BS_ISYSTEM_H_
#define _BS_ISYSTEM_H_

#include "IApp.h"
#include "AppMsg.h"

typedef unsigned char systemState;

#define SYSTEM_STATE_UNREADY	0x31
#define SYSTEM_STATE_READY		0x32
#define SYSTEM_STATE_WORKING	0x33

class ISystem
{
public:
	virtual ~ISystem(){}
	virtual void init() = 0;
	virtual void start() = 0;
	virtual void stop() = 0;
	virtual void reset() = 0;
	virtual systemState getState() = 0;
	virtual unsigned int getSystemID() = 0;
	virtual void installApp(IApp* app) = 0;
	virtual void unInstallApp(unsigned int appID) = 0;
	virtual void sendAppMsg(AppMsg& msg, unsigned int appID) = 0;
};
#endif